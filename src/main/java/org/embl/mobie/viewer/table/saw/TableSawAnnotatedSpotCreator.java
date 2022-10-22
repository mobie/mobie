package org.embl.mobie.viewer.table.saw;

import org.embl.mobie.viewer.table.ColumnNames;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TableSawAnnotatedSpotCreator implements TableSawAnnotationCreator< TableSawAnnotatedSpot >
{
	private final int spotIDColumnIndex;
	private final int xColumnIndex;
	private final int yColumnIndex;
	private final int zColumnIndex;
	private int timePointColumnIndex;

	public TableSawAnnotatedSpotCreator( Table table )
	{
		final List< String > columnNames = table.columnNames();
		spotIDColumnIndex = columnNames.indexOf( ColumnNames.SPOT_ID );
		xColumnIndex = columnNames.indexOf( ColumnNames.SPOT_X );
		yColumnIndex = columnNames.indexOf( ColumnNames.SPOT_Y );
		zColumnIndex = columnNames.indexOf( ColumnNames.SPOT_Z );
		timePointColumnIndex = columnNames.indexOf( ColumnNames.TIMEPOINT );
	}

	@Override
	public TableSawAnnotatedSpot create( Supplier< Table > tableSupplier, int rowIndex )
	{
		final Table table = tableSupplier.get();

		final double[] position = new double[ 3 ];
		position[ 0 ] = (double) table.get( rowIndex, xColumnIndex );
		position[ 1 ] = (double) table.get( rowIndex, yColumnIndex );
		if ( zColumnIndex > -1 )
			position[ 2 ] = (double) table.get( rowIndex, yColumnIndex );

		int label = ( int ) table.get( rowIndex, spotIDColumnIndex );

		int timePoint = 0;
		if ( timePointColumnIndex > -1 )
			timePoint = ( int ) table.get( rowIndex, timePointColumnIndex );

		String source = table.name();
		String uuid = source + ";" + timePoint + ";" + label;

		return new TableSawAnnotatedSpot( tableSupplier, rowIndex, label, position, timePoint, source, uuid );
	}
}
