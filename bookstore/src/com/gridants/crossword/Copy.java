package com.gridants.crossword;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

public class Copy {

	public static void CopytoSD(String internaldb, String sddb, Context c) {
		try {

			// Get the path of the sdcard
			File sd = Environment.getExternalStorageDirectory();

			// If the write permission for sd card is given
			if (sd.canWrite()) {

				// The path to which the database needs to be saved
				File file = new File(Environment.getExternalStorageDirectory()
						.toString() + "/navigationApp");
				// File file_assets = new File("assets/navigationApp");

				// If the path do not exit, create the necessary folders
				if (!file.exists()) {
					File folder = new File(Environment
							.getExternalStorageDirectory().toString()
							+ "/navigationApp");
					folder.mkdirs();
				}

				// For storing into Assets , can be commented if not required
				/*
				 * if (!file_assets.exists()) { File folder = new
				 * File("assets/navigationApp"); folder.mkdirs(); }
				 */

				String sourcedbpath = Environment.getDataDirectory()
						+ "/data/com.gridants.navtestapp/databases/";

				String destinationdbpath = Environment
						.getExternalStorageDirectory() + "/navigationApp/";

				// Creating files for source and destination
				File sourceDB = new File(sourcedbpath, internaldb);
				File destinationDB = new File(destinationdbpath, sddb);

				// Check for writing into assets

				// If the database in internal location exists then copy the
				// database file into new location
				if (sourceDB.exists()) {
					Log.i("Settings Backup", "Taking Backup");

					// Defining source and Destination files writing and reading
					// streams
					FileChannel src = new FileInputStream(sourceDB)
							.getChannel();
					FileChannel dst = new FileOutputStream(destinationDB)
							.getChannel();

					// transfer the data into destination file
					dst.transferFrom(src, 0, src.size());

					// close all the files
					src.close();
					dst.close();
				}
			} else {
				Log.d("Test", "SD card write permissions not available");
			}
		} catch (Exception e) {
			Log.e("Settings Backup", e.toString());
		}
	}

	public static void CopytoInternal(String internaldb, String sddb, Context c) {
		try {

			// Get the path of the sdcard
			File sd = Environment.getExternalStorageDirectory();

			// If the write permission for sd card is given
			if (sd.canWrite()) {

				// The path to which the database needs to be saved
				File file = new File(Environment.getExternalStorageDirectory()
						.toString() + "/navigationApp");
				// File file_assets = new File("assets/navigationApp");

				// If the path do not exit, create the necessary folders
				if (!file.exists()) {
					File folder = new File(Environment
							.getExternalStorageDirectory().toString()
							+ "/navigationApp");
					folder.mkdirs();
				}

				// For storing into Assets , can be commented if not required
				/*
				 * if (!file_assets.exists()) { File folder = new
				 * File("assets/navigationApp"); folder.mkdirs(); }
				 */

				String destinationdbpath = Environment.getDataDirectory()
						+ "/data/com.gridants.navtestapp/databases/";

				String sourcedbpath = Environment.getExternalStorageDirectory()
						+ "/navigationApp/";

				// Creating files for source and destination
				File sourceDB = new File(sourcedbpath, sddb);
				File destinationDB = new File(destinationdbpath, internaldb);

				// Check for writing into assets

				// If the database in internal location exists then copy the
				// database file into new location
				if (sourceDB.exists()) {
					Log.i("Settings Backup", "Taking Backup");

					// Defining source and Destination files writing and reading
					// streams
					FileChannel src = new FileInputStream(sourceDB)
							.getChannel();
					FileChannel dst = new FileOutputStream(destinationDB)
							.getChannel();

					// transfer the data into destination file
					dst.transferFrom(src, 0, src.size());

					// close all the files
					src.close();
					dst.close();
				}
			} else {
				Log.d("Test", "SD card write permissions not available");
			}
		} catch (Exception e) {
			Log.e("Settings Backup", e.toString());
		}
	}

	public static void copyFile(String outFilePath, String inFilePath)
			throws IOException {

		// Open your local db as the input stream
		if (new File(inFilePath).exists()) {
			InputStream myInput = new FileInputStream(inFilePath);

			// Open the empty db as the output stream
			OutputStream myOutput = new FileOutputStream(outFilePath);

			// transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}

			// Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();
		} else {
			Log.d("Database", "Input file not found");
		}
	}

	// Method to copy map file from assets to sd card
	public static void copyMap(AssetManager mgr, String ObjectName) {
		try {
			// Get the path of the sdcard
			File sd = Environment.getExternalStorageDirectory();

			String outFilePath = Environment.getExternalStorageDirectory()
					.toString() + "/osmdroid";

			// If the write permission for sd card is given
			if (sd.canWrite()) {
				// The path to which the database needs to be saved
				File file = new File(Environment.getExternalStorageDirectory()
						.toString() + "/osmdroid");

				// Remove the previous map from sd card
				deleteDirectory(file);

				// If the path do not exit, create the necessary folders
				if (!file.exists()) {
					File folder = new File(Environment
							.getExternalStorageDirectory().toString()
							+ "/osmdroid");
					folder.mkdirs();
				}

				file = new File(Environment.getExternalStorageDirectory()
						.toString() + "/osmdroid" + "/" + ObjectName);

				if (!file.exists()) {
					// Open your local db as the input stream
					InputStream myInput = mgr.open(ObjectName);

					// Open the empty db as the output stream
					OutputStream myOutput = new FileOutputStream(outFilePath
							+ "/" + ObjectName);

					// transfer bytes from the inputfile to the outputfile
					byte[] buffer = new byte[1024];
					int length;
					while ((length = myInput.read(buffer)) > 0) {
						myOutput.write(buffer, 0, length);
					}

					// Close the streams
					myOutput.flush();
					myOutput.close();
					myInput.close();
					Log.i("Test", "Map Copied");
				}
			}
		} catch (Exception e) {
			Log.w("Settings Backup", e);
		}
	}

	public static boolean deleteDirectory(File path) {
		// TODO Auto-generated method stub
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	public static void listcontent(Context c) {

		File mfile = new File(Environment.getExternalStorageDirectory()
				+ "/navigationApp/");
		File[] list = mfile.listFiles();

		int numfile = list.length;

		String[] names = new String[numfile];

		if (list.length != 0) {
			for (int i = 0; i < list.length; i++) {
				names[i] = list[i].getName();
			}
		}

		for (int i = 0; i < numfile; i++) {
			Log.i("Test", "SD Card: " + names[i]);
		}

		mfile = new File(Environment.getDataDirectory()
				+ "/data/com.gridants.navtestapp/databases/");

		list = mfile.listFiles();

		numfile = list.length;

		names = new String[numfile];

		if (list.length != 0) {
			for (int i = 0; i < list.length; i++) {
				names[i] = list[i].getName();
			}
		}

		for (int i = 0; i < numfile; i++) {
			Log.i("Test", "Internal database: " + names[i]);
		}

	}
}