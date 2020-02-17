package service;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

import db.DatabaseConnectionService;

public class UserService {
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	private DatabaseConnectionService dbService = null;

	public UserService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean useApplicationLogins() {
		return true;
	}

	public boolean login(String username, String password) {
		byte[] passwordsalt;

		String query = "SELECT PasswordHash,PasswordSalt FROM [Login] WHERE Username = ?";
		PreparedStatement stmt;
		String tocheck = "";
		String passwordhash = "";

		try {
//			String passwordhash = "";

//			System.out.println(rs0.getString(1));
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == false) {
				System.out.println(1);
				JOptionPane.showMessageDialog(null, "Login failed!");
				return false;
			}
			tocheck = rs.getString(1);
			passwordsalt = dec.decode(rs.getString(2));
			passwordhash = hashPassword(passwordsalt, password);

//			System.out.println(password);
//			System.out.println(rs.getString(2));
//			System.out.println(tocheck);
//			System.out.println(passwordhash);

		} catch (SQLException e) {
			// TOO Auto-generated catch block
			e.printStackTrace();
		}
		if (tocheck.equals(passwordhash)) {
			return true;
		} else {
			System.out.println(2);

			JOptionPane.showMessageDialog(null, "Login failed!");
			return false;

		}

	}

	public boolean register(String username, String password) {
		
		byte[] passwordsalt = getNewSalt();
		String passwordhash = hashPassword(passwordsalt, password);
		CallableStatement tocall = null;
		int returnval = -5;
		String passwordsalttostring = getStringFromBytes(passwordsalt);

		try {
			tocall = this.dbService.getConnection().prepareCall("{ ? = call Register(?,?,?) }");

			tocall.registerOutParameter(1, Types.INTEGER);
			tocall.setString(2, username);
			tocall.setString(3, passwordsalttostring);
			tocall.setString(4, passwordhash);

			tocall.execute();
			returnval = tocall.getInt(1);
		} catch (SQLException e) {
			// TOO Auto-generated catch block
			e.printStackTrace();
		}

		if (returnval == 0) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Registration failed!");
			return false;
		}

	}

	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;

	}

	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

}
