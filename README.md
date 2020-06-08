# MoBIE

MultiModal Big Image Data Sharing and Exploration

MoBIE is a framework for sharing and exploring large multi-modal image datasets.

Example projects:
- A cellular atlas for *Platynereis dumerilii*, the [PlatyBrowser](https://github.com/mobie-org/platybrowser-datasets)


## MoBIE software

For now, we provide two software tools to explore and generate MoBIE projects:
- [mobie-viewer-fiji](https://github.com/mobie-org/mobie-viewer-fiji) is a Fiji plugin to explore MoBIE projects from local or remote sources.
- [mobie-utils-python](https://github.com/mobie-org/mobie-utils-python) is a python library to generate data in the MoBIE data storage layout.


## Data storage

To be accessible via MoBIE tools, your data needs to be organized as a **project**.
One project can contain several **datasets**.

See the folder `data` in this repository for an example project. It uses a small subset of [the data from the PlatyBrowser](https://github.com/mobie-org/platybrowser-datasets/tree/master/data).

### Project layout


### Dataset layout

The folder for a given version follows this structure:
```
+images
|  +--images.json
|  +--local
|  +--remote
+misc
|  +--bookmarks
+tables
+README.txt
```

- [images/images.json](https://github.com/platybrowser/pymmb#imagesjson): json which lists the avaialable image data and associated display options
- [images/local](https://github.com/platybrowser/pymmb#supported-data-formats): metadata for image data stored locally, in bdv.xml data format
- [images/remote](https://github.com/platybrowser/pymmb#supported-data-formats): metadata for image data stored remotely in a s3 compatible cloud store, in bdv.xml data format
- misc: miscellaneous data, including [bookmarks](https://github.com/platybrowser/pymmb#bookmarksjson)
- [tables](https://github.com/platybrowser/pymmb#supported-data-formats): tabular data associated with image data that has object ids, e.g. segmentations
- README.txt: description of this version (optional)

#### images.json

TODO describe images.json


#### bookmarks.json

TODO describe bookmark format


### Data formats

The MMB uses [BigDataViewer](https://imagej.net/BigDataViewer) to browse volumetric image data stored locally or on a s3 compatible cloud object store.
Hence the data must be provided in one of these three formats:
- [bdv.hdf5](https://imagej.net/BigDataViewer#About_the_BigDataViewer_data_format) for local data
- [bdv.n5](https://github.com/bigdataviewer/bigdataviewer-core/blob/master/BDV%20N5%20format.md) for local data
- [bdv.n5.s3](https://github.com/saalfeldlab/n5-aws-s3) for data stored on a s3 compatible object store. Note that this is not part of the official bdv spec yet, [we are working towards merging it](https://github.com/bigdataviewer/bigdataviewer-core/issues/80)

Tables are stored as `tab separated values` and can be read from the filesystem or a githost.
For each image with associated tables, the tables are stored in `tables/<IMAGE-NAME>/`.
This folder must contain a table called `default.csv`, it can contain additional tables listed in a file `additional_tables.txt`. All tables must contain the column `label_id` linking its rows to objects in the image.



## Contributors


## Citation

If you use the MMB for your research, please cite [Whole-body integration of gene expression and single-cell morphology](https://www.biorxiv.org/content/10.1101/2020.02.26.961037v1).
