/**
 * 
 */
package com.obiectumclaro.file.reader;

import com.obiectumclaro.file.reader.exception.FileReadingException;

/**
 * @author fausto
 *
 */
public interface FileReader {

	public void read() throws FileReadingException;
}
