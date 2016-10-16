package com.lte.msw.standalone.main;

import java.io.File;

import com.lte.msw.standalone.model.Path;

public class ProgramDirectory {
	
	public static void checkDirectories() {
		File workingDirectory = new File(Path.WORKBENCH_DIRECTORY);
		File servercheckDirectory = new File(Path.SERVER_CHECK);
		File serverversionsDirectory = new File(Path.SERVER_VERSIONS);
		File serverpoolDirectory = new File(Path.SERVER_DIRECTORY);
		if (workingDirectory.exists() && workingDirectory.isDirectory()) {
			if (!(servercheckDirectory.exists() && servercheckDirectory.isDirectory())) {
				servercheckDirectory.mkdir();
			}
			if (!(serverversionsDirectory.exists() && serverversionsDirectory.isDirectory())) {
				serverversionsDirectory.mkdir();
			}
			if (!(serverpoolDirectory.exists() && serverpoolDirectory.isDirectory())) {
				serverpoolDirectory.mkdir();
			}
		} else {
			workingDirectory.mkdir();
			servercheckDirectory.mkdir();
			serverversionsDirectory.mkdir();
			serverpoolDirectory.mkdir();
		}
	}
	
}
