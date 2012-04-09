/* Copyright 2011-2012 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lesscss;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * Ant task for LESS compiler
 * 
 * @author BKGEN
 *
 */
public class LessCompilerTask extends Task {
	private String input;
	private String output;	
	private boolean compress;	
	private LessCompiler compiler;

	public void setInput(String input) {
		this.input = input;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	public void setCompress(boolean compress) {
		this.compress = compress;
	}

	public void execute() {
		this.compiler = new LessCompiler();
		try {
			if (input == null) {
				throw new BuildException("No input set.");
			}
			if (output == null) {
				throw new BuildException("No output set.");
			}
			
			this.log("Compiling input: " 
					+ input 
					+ " to output: " 
					+ output 
					+ " (compress: " + compress + ")", Project.MSG_DEBUG);
			
			File inputFile = new File(input);
			File outputFile = new File(output);
			
			this.compiler.setCompress(compress);
			this.compiler.compile(inputFile, outputFile);
		} catch (LessException e) {
			throw new BuildException("Error compiling less file", e);
		} catch (IOException e) {
			throw new BuildException("Error reading/writing to files", e);
		}
	}
}
