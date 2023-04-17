package org.irri.iric.portal.hdf5;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.SnpsStringDAO;
import org.irri.iric.portal.domain.GenotypeRunPlatform;
import org.irri.iric.portal.domain.SnpsAllvarsPos;

//import hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.object.Dataset;
import ncsa.hdf.object.Group;
import ncsa.hdf.object.h5.*;

//import org.irri.iric.portal.AppContext;

public class H5Dataset implements SnpsStringDAO {

	protected String filename;
	private Dataset dataset;
	private H5File h5file;
	private H5ReadMatrix matrixReader;

	// expect all varididx are var_ids
	private int varid_offset = 0;
	private Map<Integer, BigDecimal> mapIdx2SampleId = null;
	private Map<BigDecimal, Integer> mapSampleId2Idx = null;

	/*
	 * Code to manually laod hdf5 library if not found in the system
	 * 
	 */
	/*
	 * static { AppContext.debug("java.library.path=" +
	 * System.getProperty("java.library.path")); try {
	 * 
	 * 
	 * 
	 * if(AppContext.isWindows()) {
	 * AppContext.debug("Loading Native code library jhdf5.dll");
	 * System.setProperty("java.library.path",
	 * System.getProperty("java.library.path")+
	 * ";E:\\HDF_Group\\HDFView\\2.10.1\\lib" );
	 * AppContext.debug("java.library.path=" +
	 * System.getProperty("java.library.path")); System.load(
	 * "E:\\HDF_Group\\HDFView\\2.10.1\\lib\\jhdf5.dll");
	 * AppContext.debug("loading jhdf5.dll ... success"); } else {
	 * AppContext.debug("Loading Native code library jhdf5.lib"); System.load(
	 * AppContext.getFlatfilesDir() + "lib/jhdf5.lib");
	 * AppContext.debug("loading jhdf5.lib ... success"); }
	 * 
	 * 
	 * 
	 * } catch (UnsatisfiedLinkError e) {
	 * System.err.println("Native code library failed to load.\n" + e);
	 * System.err.println(e.getMessage()); e.printStackTrace(); } catch (Exception
	 * e) { System.err.println(e.getMessage()); e.printStackTrace(); }
	 * 
	 * };
	 */

	/*
	 * public H5Dataset(String filename) { this(filename, new H5ReadCharmatrix()); }
	 */

	/*
	 * public H5Dataset(String filename, H5ReadMatrix reader) { super();
	 * this.filename = filename; matrixReader = reader;
	 * //if(filename.contains("gopal92")) varid_offset=4592; }
	 */

	/*
	 * public H5Dataset(String filename, H5ReadMatrix reader, int varidoffset) {
	 * super(); this.filename = AppContext.getFlatfilesDir()+ filename; matrixReader
	 * = reader; varid_offset=varidoffset;
	 * 
	 * }
	 */

	public H5Dataset(String filename, H5ReadMatrix reader, Map mapIdx2SampleId) {
		super();
		this.filename = AppContext.getFlatfilesDir() + filename;
		matrixReader = reader;
		this.mapIdx2SampleId = mapIdx2SampleId;
		mapSampleId2Idx = new HashMap();
		if (mapIdx2SampleId != null) {
			Iterator it = mapIdx2SampleId.keySet().iterator();
			while (it.hasNext()) {
				Object k = it.next();
				mapSampleId2Idx.put((BigDecimal) mapIdx2SampleId.get(k), (Integer) k);
			}
		}
	}

	public Dataset getDataset() throws Exception {
		if (dataset == null) {

			// AppContext.debug( "HDF5Constants.H5F_ACC_RDONLY="+
			// HDF5Constants.H5F_ACC_RDONLY ); // HDF5Constants.H5F_ACC_RDONLY );

			try {
				System.out.println("JLP = " + System.getProperty("java.library.path") + "\nfilename=" + filename);
				 h5file = new H5File(filename, H5File.READ); // HDF5Constants.H5F_ACC_RDONLY
				// );
				//h5file = new H5File(filename, HDF5Constants.H5F_ACC_RDONLY);
				System.out.println("h5file=" + h5file);
			} catch (Exception ex) {
				AppContext.debug(filename + " H5File Exception\nh5file=" + h5file);
				ex.printStackTrace();
			}
			// h5file = new H5File( filename); //, HDF5Constants.H5F_ACC_RDONLY);
			// h5file = new H5File( filename, H5File.READ ); //,
			// HDF5Constants.H5F_ACC_RDONLY);

			AppContext.debug(filename + " loaded");

			// open file and retrieve the file structure
			h5file.open();
			Group root = (Group) ((javax.swing.tree.DefaultMutableTreeNode) h5file.getRootNode()).getUserObject();
			dataset = (Dataset) root.getMemberList().get(0);

			long[] maxdims = dataset.getMaxDims();
			if (maxdims != null) {
				StringBuffer buff = new StringBuffer();
				for (int idim = 0; idim < maxdims.length; idim++) {
					buff.append("hdf5 file " + filename + " dim[" + idim + "]=" + maxdims[idim] + ",");
				}
				// AppContext.debug(buff.toString());
				AppContext.debug(buff.toString());
			}
		}
		return dataset;
	}

	public void close() {
		try {
			h5file.close();
			AppContext.debug("hdf5 file " + filename + " closed.");
		} catch (Exception ex) {
		}
	}

	@Override
	public void finalize() {
		close();
	}

	@Override
	public Map readSNPString(String chr, int startIdx, int endIdx) {
		try {

			AppContext.debugIterate("H5 querying all " + this.filename + " [" + startIdx + "-" + endIdx + "]  0-based");
			return matrixReader.read(this, new InputParamsIdxs(startIdx, endIdx)).offsetVarId(varid_offset)
					.remapVarId(mapIdx2SampleId).getMapVar2String();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map readSNPString(String chr, int posIdxs[], int starvarid, int endvarid) {
		try {
			AppContext.debug("H5 querying " + this.filename + " " + posIdxs.length + " positions, varid " + starvarid
					+ "-" + endvarid);
			return matrixReader
					.read(this, new InputParamsIdxs(posIdxs, starvarid - varid_offset, endvarid - varid_offset))
					.offsetVarId(varid_offset).remapVarId(mapIdx2SampleId).getMapVar2String();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map readSNPString(String chr, int starvarid, int endvarid, int posstartendIdxs[][]) {
		try {
			AppContext.debug("H5 querying " + this.filename + " " + posstartendIdxs.length + " positions ranges, varid "
					+ starvarid + "-" + endvarid);
			return matrixReader
					.read(this, new InputParamsIdxs(posstartendIdxs, starvarid - varid_offset, endvarid - varid_offset))
					.offsetVarId(varid_offset).remapVarId(mapIdx2SampleId).getMapVar2String();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map readSNPString(String chr, int posIdxs[]) {
		try {
			AppContext.debug("H5 querying " + this.filename + " " + posIdxs.length + " positions");
			return matrixReader.read(this, new InputParamsIdxs(posIdxs)).offsetVarId(varid_offset)
					.remapVarId(mapIdx2SampleId).getMapVar2String();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map readSNPString(Set<BigDecimal> colVarids, String chr, int posIdxs[]) {
		try {
			// order varids based on file ordering for 1pass/smooth disk read
			Set orderedVarids = new TreeSet(colVarids);
			Iterator<BigDecimal> itVarid = orderedVarids.iterator();

			int varids[] = new int[orderedVarids.size()];
			int icount = 0;
			while (itVarid.hasNext()) {
				if (mapSampleId2Idx == null || mapSampleId2Idx.isEmpty())
					varids[icount] = itVarid.next().intValue() - varid_offset;
				else
					varids[icount] = mapSampleId2Idx.get(itVarid.next().intValue() - varid_offset).intValue();
				icount++;
			}
			AppContext.debug(
					"H5 querying " + varids.length + " vars " + this.filename + " " + posIdxs.length + " positions");
			return matrixReader.read(this, new InputParamsIdxs(posIdxs, varids)).offsetVarId(varid_offset)
					.remapVarId(mapIdx2SampleId).getMapVar2String();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map readSNPString(Set<BigDecimal> colVarids, String chr, int startIdx, int endIdx) {
		try {
			Set orderedVarids = new TreeSet(colVarids);
			Iterator<BigDecimal> itVarid = orderedVarids.iterator();

			int varids[] = new int[orderedVarids.size()];
			int icount = 0;
			while (itVarid.hasNext()) {

				// varids[icount]=itVarid.next().intValue()-varid_offset;
				if (mapSampleId2Idx == null || mapSampleId2Idx.isEmpty())
					varids[icount] = itVarid.next().intValue() - varid_offset;
				else
					varids[icount] = mapSampleId2Idx.get(itVarid.next().intValue() - varid_offset).intValue();
				icount++;
			}

			AppContext.debug("H5 querying " + varids.length + " vars " + this.filename + " [" + startIdx + "-" + endIdx
					+ "]  0-based");
			return matrixReader.read(this, new InputParamsIdxs(startIdx, endIdx, varids)).offsetVarId(varid_offset)
					.remapVarId(mapIdx2SampleId).getMapVar2String();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map readSNPString(Set colVarids, String chr, int posidxstartend[][]) {

		try {
			// order varids based on file ordering for 1pass/smooth disk read
			Set orderedVarids = new TreeSet(colVarids);
			Iterator<BigDecimal> itVarid = orderedVarids.iterator();

			int varids[] = new int[orderedVarids.size()];
			int icount = 0;
			while (itVarid.hasNext()) {
				// varids[icount]=itVarid.next().intValue()-varid_offset;
				if (mapSampleId2Idx == null || mapSampleId2Idx.isEmpty())
					varids[icount] = itVarid.next().intValue() - varid_offset;
				else
					varids[icount] = mapSampleId2Idx.get(itVarid.next().intValue() - varid_offset).intValue();
				icount++;
			}
			AppContext.debug("H5 querying " + varids.length + " vars " + this.filename + " " + posidxstartend.length
					+ " ranges");
			return matrixReader.read(this, new InputParamsIdxs(posidxstartend, varids)).offsetVarId(varid_offset)
					.remapVarId(mapIdx2SampleId).getMapVar2String();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	@Override
	public Map readSNPString(String chr, int posidxstartend[][]) {

		try {
			// order varids based on file ordering for 1pass/smooth disk read
			AppContext.debug("H5 querying " + this.filename + " " + posidxstartend.length + " ranges");
			return matrixReader.read(this, new InputParamsIdxs(posidxstartend)).offsetVarId(varid_offset)
					.remapVarId(mapIdx2SampleId).getMapVar2String();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map[] readSNPString(List<SnpsAllvarsPos> listpos) {
		return null;
	}

	@Override
	public Map[] readSNPString(GenotypeRunPlatform run, String chr, List<SnpsAllvarsPos> listpos) {
		return null;
	}

	@Override
	public Map[] readSNPString(GenotypeRunPlatform run, Set<BigDecimal> colVarids, String chr,
			List<SnpsAllvarsPos> listpos) {
		return null;
	}

}
