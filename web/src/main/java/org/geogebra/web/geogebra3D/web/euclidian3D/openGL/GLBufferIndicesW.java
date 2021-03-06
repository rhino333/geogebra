package org.geogebra.web.geogebra3D.web.euclidian3D.openGL;

import org.geogebra.common.geogebra3D.euclidian3D.openGL.GLBufferIndices;

/**
 * buffers for openGL
 * 
 * @author mathieu
 *
 */
public class GLBufferIndicesW implements GLBufferIndices {

	private MyInt16Array impl;

	/**
	 * constructor from float array
	 */
	public GLBufferIndicesW() {
		isEmpty = true;
		currentLength = 0;
	}

	private boolean isEmpty;

	private int currentLength;

	@Override
	public boolean isEmpty() {
		return isEmpty;
	}

	@Override
	public void setEmpty() {
		isEmpty = true;
	}

	@Override
	public void allocate(int length) {
		// allocate buffer only at start and when length change
		if (impl == null || impl.getLength() < length) {
			// This may be null in IE10
			impl = MyInt16Array.create(length);
		}

		index = 0;

	}

	private int index = 0;

	@Override
	public void setLimit(int length) {
		currentLength = length;
		isEmpty = false;
	}

	@Override
	public void put(short value) {
		if (impl == null) {
			return;
		}
		impl.set(index, value);
		index++;
	}

	@Override
	public short get() {
		short ret = (short) impl.get(index);
		index++;
		return ret;
	}

	@Override
	public void rewind() {
		index = 0;
	}

	@Override
	public int capacity() {
		return currentLength;
	}

	@Override
	public void array(short[] ret) {
		if (impl == null) {
			return;
		}
		for (int i = 0; i < ret.length; i++) {
			ret[i] = (short) impl.get(i);
		}
	}

	/**
	 * 
	 * @return buffer
	 */
	public MyInt16Array getBuffer() {
		return impl;
	}

}
