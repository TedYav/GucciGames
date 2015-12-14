package voogasalad_GucciGames.gameData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class GameFileHelper {
	//
	// public static void main(String[] args) {
	// GameFileHelper g = new GameFileHelper();
	// try {
	// g.copyResource("resources/images/units/duvall.png",
	// "./src/games/Duvall_Tag/resources/images/units/duvall.png");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// //g.renameDirectory("./src/games/Duvall_Tag/", "./src/games/PWNAGE/");
	// ArrayList<String> files = new ArrayList<>();
	// g.listf("resources/images/", files, Arrays.asList("jpg", "png"));
	// System.out.println(files);
	// }
	//
	public void copyResource(String sourceURI, String destURI) throws IOException, NoSuchFileException {
		File source = new File(sourceURI);
		File dest = new File(destURI);
		// System.out.println("TRYING TO COPY " + sourceURI + " TO " + destURI);
		if (!dest.exists()) {
			Files.copy(source.toPath(), dest.toPath());
			// System.out.println("COPIED " + sourceURI + " TO " + destURI);
		}
	}

	public List<String> getMatchingFiles(String path, List<String> extensions) {
		ArrayList<String> files = new ArrayList<>();
		listf(path, files, extensions);
		return files;
	}

	public void makeDirectory(String path) {
		File directory = new File(path);
		// System.out.println("TRYING TO MAKE DIRECTORY " + path);
		if (!directory.exists()) {
			directory.mkdir();
			// System.out.println("CREATED DIRECTORY " + path);
		}
	}

	private void listf(String directoryName, List<String> files, List<String> extensions) {
		File directory = new File(directoryName);
		// System.out.println("STARTING IN DIRECTORY " + directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		if (fList.length > 0) {
			for (File file : fList) {
				if (file.isFile()) {
					if (hasExtension(file, extensions)) {
						files.add(directoryName + file.getName());
					}
				} else if (file.isDirectory()) {
					listf(directoryName + file.getName() + "/", files, extensions);
				}
			}
		}
	}

	private boolean hasExtension(File file, List<String> extensions) {
		for (String extension : extensions) {
			if (file.getName().endsWith(extension)) {
				return true;
			}
		}
		return false;
	}

	// public void renameDirectory(String source, String dest) {
	// File sourceDir = new File(source);
	// File destDir = new File(dest);
	// if(sourceDir.isDirectory()){
	// System.out.println("RENAMING " + source + " TO " + dest);
	// sourceDir.renameTo(destDir);
	// }
	// }

}
