/*-
 * #%L
 * Fiji viewer for MoBIE projects
 * %%
 * Copyright (C) 2018 - 2022 EMBL
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package org.embl.mobie.viewer.transform.image;

import net.imglib2.realtransform.AffineTransform3D;
import org.embl.mobie.viewer.ImageStore;
import org.embl.mobie.viewer.MultiThreading;
import org.embl.mobie.viewer.source.Image;
import org.embl.mobie.viewer.transform.TransformHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ImageGridTransformer
{
	public ImageGridTransformer()
	{
	}

	// This currently also registers the transformed images with
	// the ImageStore. It would be cleaner to return the transformed images.
	public void transform( List< List< ? extends Image< ? > > > nestedImages, @Nullable List< List< String > > transformedNames, List< int[] > positions, double[] tileRealDimensions, boolean centerAtOrigin )
	{
		final int numGridPositions = nestedImages.size();

		final ArrayList< Future< ? > > futures = MultiThreading.getFutures();
		for ( int gridIndex = 0; gridIndex < numGridPositions; gridIndex++ )
		{
			int finalGridIndex = gridIndex;
			futures.add( MultiThreading.executorService.submit( () -> {
				try
				{
					final List< ? extends Image< ? > > images = nestedImages.get( finalGridIndex );
					final double translationX = tileRealDimensions[ 0 ] * positions.get( finalGridIndex )[ 0 ];
					final double translationY = tileRealDimensions[ 1 ] * positions.get( finalGridIndex )[ 1 ];
					// keep same names...
					List< String > transformedImageNames = images.stream().map( image -> image.getName() ).collect( Collectors.toList() );
					// ...or give new names if provided.
					if ( transformedNames != null )
						transformedImageNames = transformedNames.get( finalGridIndex );

					translate( images, transformedImageNames, centerAtOrigin, translationX, translationY );
				}
				catch ( Exception e )
				{
					throw ( e );
				}
			} ) );
		}
		MultiThreading.waitUntilFinished( futures );
	}

	public static void translate( List< ? extends Image< ? > > images, List< String > transformedNames, boolean centerAtOrigin, double translationX, double translationY )
	{
		for ( Image< ? > image : images )
		{
			AffineTransform3D translationTransform = TransformHelper.create2dTranslationTransform( translationX, translationY, image, centerAtOrigin );
			final AffineTransformedImage< ? > transformedImage = new AffineTransformedImage<>( image, transformedNames.get( images.indexOf( image ) ), translationTransform );
			ImageStore.putImage( transformedImage );
		}
	}
}
