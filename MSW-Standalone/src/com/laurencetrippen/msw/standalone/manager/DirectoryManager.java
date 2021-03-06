package com.laurencetrippen.msw.standalone.manager;

import java.io.File;

import com.laurencetrippen.msw.standalone.model.abstracts.ResourcePath;

public class DirectoryManager {
	
	public static void checkDirectories() {
		File workingDirectory = new File(ResourcePath.WORKBENCH_DIRECTORY);
		File servercheckDirectory = new File(ResourcePath.SERVER_CHECK);
		File serverversionsDirectory = new File(ResourcePath.SERVER_VERSIONS);
		File serverpoolDirectory = new File(ResourcePath.SERVER_DIRECTORY);
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
