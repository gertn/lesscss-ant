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

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest({ LessCompilerTask.class })
@RunWith(PowerMockRunner.class)
public class LessCompilerTaskTest {

	private static final String OUTPUT_FILE = "output_file";
	private static final String INPUT_FILE = "input_file";

	@Mock
	private LessCompiler lessCompiler;
	@Mock
	private Project project;

	@Test
	public void testExecuteWithDefaultCompressFalse() throws Exception {

		whenNew(LessCompiler.class).withNoArguments().thenReturn(lessCompiler);

		LessCompilerTask lessCompilerTask = new LessCompilerTask();
		lessCompilerTask.setInput(INPUT_FILE);
		lessCompilerTask.setOutput(OUTPUT_FILE);
		lessCompilerTask.setProject(project);

		lessCompilerTask.execute();

		verify(lessCompiler).setCompress(false);
		verify(lessCompiler).compile(new File(INPUT_FILE),
				new File(OUTPUT_FILE));
		verify(project).log(eq(lessCompilerTask), anyString(),
				eq(Project.MSG_DEBUG));

	}

	@Test
	public void testExecuteWithCompressTrue() throws Exception {

		whenNew(LessCompiler.class).withNoArguments().thenReturn(lessCompiler);

		LessCompilerTask lessCompilerTask = new LessCompilerTask();
		lessCompilerTask.setInput(INPUT_FILE);
		lessCompilerTask.setOutput(OUTPUT_FILE);
		lessCompilerTask.setProject(project);
		lessCompilerTask.setCompress(true);

		lessCompilerTask.execute();

		verify(lessCompiler).setCompress(true);
		verify(lessCompiler).compile(new File(INPUT_FILE),
				new File(OUTPUT_FILE));
		verify(project).log(eq(lessCompilerTask), anyString(),
				eq(Project.MSG_DEBUG));
	}

	@Test(expected = BuildException.class)
	public void testInputRequired() throws Exception {
		LessCompilerTask lessCompilerTask = new LessCompilerTask();
		lessCompilerTask.setOutput(OUTPUT_FILE);

		lessCompilerTask.execute();
	}

	@Test(expected = BuildException.class)
	public void testOutputRequired() throws Exception {
		LessCompilerTask lessCompilerTask = new LessCompilerTask();
		lessCompilerTask.setInput(INPUT_FILE);

		lessCompilerTask.execute();
	}

}
