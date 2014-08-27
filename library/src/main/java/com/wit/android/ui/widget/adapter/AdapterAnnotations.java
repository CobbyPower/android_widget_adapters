/*
 * =================================================================================================
 *                    Copyright (C) 2014 Martin Albedinsky [Wolf-ITechnologies]
 * =================================================================================================
 *         Licensed under the Apache License, Version 2.0 or later (further "License" only).
 * -------------------------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy of this License
 * you may obtain at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * You can redistribute, modify or publish any part of the code written within this file but as it
 * is described in the License, the software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF ANY KIND.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 * =================================================================================================
 */
package com.wit.android.ui.widget.adapter;

import java.lang.annotation.Annotation;

/**
 * Helper class to obtain class annotations.
 *
 * @author Martin Albedinsky
 */
final class AdapterAnnotations {

	/**
	 * Same as {@link #obtainAnnotationFrom(Class, Class, Class)} with no <var>maxSuperClass</var>
	 * specified.
	 */
	public static <A extends Annotation> A obtainAnnotationFrom(Class<?> fromClass, Class<A> classOfAnnotation) {
		return obtainAnnotationFrom(fromClass, classOfAnnotation, null);
	}

	/**
	 * Obtains the specified type of an annotation from the given <var>fromClass</var> if it is
	 * presented.
	 *
	 * @param fromClass         The class from which should be the requested annotation obtained.
	 * @param classOfAnnotation The class of the requested annotation.
	 * @param maxSuperClass     If <code>not null</code>, this method will be also called (recursively)
	 *                          for all super classes of the given annotated class (max to the specified
	 *                          <var>maxSuperClass</var>) until the requested annotation is presented
	 *                          and obtained, otherwise annotation will be obtained only from the given
	 *                          annotated class.
	 * @param <A>               The type of the requested annotation.
	 * @return Obtained annotation or <code>null</code> if the requested annotation is not presented
	 * for the given class.
	 * @see #obtainAnnotationFrom(Class, Class)
	 */
	public static <A extends Annotation> A obtainAnnotationFrom(Class<?> fromClass, Class<A> classOfAnnotation, Class<?> maxSuperClass) {
		if (!fromClass.isAnnotationPresent(classOfAnnotation) && maxSuperClass != null) {
			final Class<?> parent = fromClass.getSuperclass();
			if (parent != null && !parent.equals(maxSuperClass)) {
				return obtainAnnotationFrom(parent, classOfAnnotation, maxSuperClass);
			}
		}
		return fromClass.getAnnotation(classOfAnnotation);
	}
}
