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

See the folder `/data` in this repository for an example project. It uses a small subset of [the data from the PlatyBrowser](https://github.com/mobie-org/platybrowser-datasets/tree/master/data).

### Project layout

```
+datasets.json
+dataset1/
+dataset2/
```


### Dataset layout

The folder for a given version follows this structure:
```
+images/
|  +--images.json
|  +--local/
|  +--remote/
+misc/
|  +--bookmarks
+tables/
+README.txt
```

- [images/images.json](): json which lists the avaialable image data and associated display options
- [images/local](): metadata for image data stored locally, in bdv.xml data format
- [images/remote](): metadata for image data stored remotely in a s3 compatible cloud store, in bdv.xml data format
- [misc](): miscellaneous data
- [misc/bookmarks](): bookmarks
- [tables](): tables for image data with associated objects, e.g. segmentations
- README.txt: description of this version (optional)

#### images

TODO describe images.json and image format

MoBIE uses the [BigDataViewer](https://imagej.net/BigDataViewer) file format to represent image data, either stored locally or on a s3 object store. This includes 3 data formats:
- [bdv.hdf5](https://imagej.net/BigDataViewer#About_the_BigDataViewer_data_format) for local data
- [bdv.n5](https://github.com/bigdataviewer/bigdataviewer-core/blob/master/BDV%20N5%20format.md) for local data
- [bdv.n5.s3](https://github.com/saalfeldlab/n5-aws-s3) for data stored on a s3 compatible object store. Note that this is not part of the official bdv spec yet, [we are working towards merging it](https://github.com/bigdataviewer/bigdataviewer-core/pull/94)

#### misc/bookmarks

TODO describe bookmark format

#### tables

TODO describe table format
Tables are stored as `tab separated values` and can be read from the filesystem or a githost.
For each image with associated tables, the tables are stored in `tables/<IMAGE-NAME>/`.
This folder must contain a table called `default.csv`, it can contain additional tables listed in a file `additional_tables.txt`. All tables must contain the column `label_id` linking its rows to objects in the image.


## Contributors


## Citation

If you use MoBIE for your research, please cite [Whole-body integration of gene expression and single-cell morphology](https://www.biorxiv.org/content/10.1101/2020.02.26.961037v1).
