package mil.nga.giat.geowave.core.store.index.numeric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import mil.nga.giat.geowave.core.index.ByteArrayId;
import mil.nga.giat.geowave.core.index.ByteArrayRange;
import mil.nga.giat.geowave.core.index.IndexMetaData;
import mil.nga.giat.geowave.core.index.InsertionIds;
import mil.nga.giat.geowave.core.index.QueryRanges;
import mil.nga.giat.geowave.core.index.lexicoder.Lexicoders;
import mil.nga.giat.geowave.core.store.base.DataStoreEntryInfo.FieldInfo;
import mil.nga.giat.geowave.core.store.index.FieldIndexStrategy;

public class NumericIndexStrategy implements
		FieldIndexStrategy<NumericQueryConstraint, Number>
{
	private static final String ID = "NUMERIC";

	public NumericIndexStrategy() {
		super();
	}

	@Override
	public byte[] toBinary() {
		return new byte[0];
	}

	@Override
	public void fromBinary(
			final byte[] bytes ) {}

	@Override
	public QueryRanges getQueryRanges(
			final NumericQueryConstraint indexedRange,
			final IndexMetaData... hints ) {
		return new QueryRanges(null, indexedRange.getRange());
	}

	@Override
	public QueryRanges getQueryRanges(
			final NumericQueryConstraint indexedRange,
			final int maxEstimatedRangeDecomposition,
			final IndexMetaData... hints ) {
		return getQueryRanges(
				indexedRange,
				hints);
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public InsertionIds getInsertionIds(
			final List<FieldInfo<Number>> indexedData ) {
		final List<ByteArrayId> insertionIds = new ArrayList<>();
		for (final FieldInfo<Number> fieldInfo : indexedData) {
			insertionIds.add(new ByteArrayId(
					toIndexByte(fieldInfo.getDataValue().getValue())));
		}
		return new InsertionIds(null, insertionIds);
	}

	@Override
	public InsertionIds getInsertionIds(
			final List<FieldInfo<Number>> indexedData,
			final int maxEstimatedDuplicateIds ) {
		return getInsertionIds(indexedData);
	}

	@Override
	public List<FieldInfo<Number>> getRangeForId(
			final InsertionIds insertionId ) {
		return Collections.emptyList();
	}

	public static final byte[] toIndexByte(
			final Number number ) {
		return Lexicoders.DOUBLE.toByteArray(number.doubleValue());
	}

	@Override
	public List<IndexMetaData> createMetaData() {
		return Collections.emptyList();
	}

}
