<img width="300" src="images/mobie-logo-highres.png"></img>


# MoBIE

MultiModal Big Image Data Sharing and Exploration

MoBIE is a framework for sharing and exploring large multi-modal image datasets.
Example projects:
- A cellular atlas for *Platynereis dumerilii*, the [PlatyBrowser](https://github.com/mobie-org/platybrowser-datasets)
- Morphogical changes in SARS-CoV2 infected cells in [EM Tomograms](https://github.com/mobie/covid-tomo-datasets) and [Volume EM](https://github.com/mobie/covid-em-datasets).

## Examples videos

- [Object browsing and attribute colouring](https://youtu.be/HENo-vq-6to)
- [Exploring gene expression in Platynereis atlas](https://youtu.be/4SR5GoSiCVE)
- [Visualising gene expression and chromatin segmentation in Platynereis atlas](https://youtu.be/tdOLelm6Vlw)

## MoBIE software

For now, we provide two software tools to explore and generate MoBIE projects:
- [mobie-viewer-fiji](https://github.com/mobie-org/mobie-viewer-fiji) is a [BigDataViewer](https://imagej.net/BigDataViewer) based [Fiji](https://fiji.sc/) plugin to explore MoBIE projects from local or remote sources.
- [mobie-utils-python](https://github.com/mobie-org/mobie-utils-python) is a python library to generate data in the MoBIE data storage layout.


## Tutorials
Tutorials for using MoBIE are available at [http://mobie.github.io/](http://mobie.github.io/), and
installation instructions [here](https://github.com/mobie/mobie-viewer-fiji)

## Data storage

To be accessible via MoBIE tools, your data needs to be organized as a **project**.
One project can contain several **datasets**.

See the folder `/data` in this repository for an example project. It uses a small subset of [the data from the PlatyBrowser](https://github.com/mobie-org/platybrowser-datasets/tree/master/data).

### Project layout

A project consists of a root folder with the file `datasets.json` that lists the available datasets and the datasets, each stored
in a separate subfolder.
```
+datasets.json
+dataset1/
+dataset2/
```

### Dataset layout

A dataset folder is structured as follows:
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

with the following elements:
- [images/images.json](https://github.com/mobie-org/mobie/blob/master/data/platynereis/images/images.json) lists the avaialable image data and stores their display options
- [images/local](https://github.com/mobie-org/mobie/tree/master/data/platynereis/images/local) contains the metadata for image data stored locally, in bdv.xml data format
- [images/remote](https://github.com/mobie-org/mobie/tree/master/data/platynereis/images/remote) contains the metadata for image data stored remotely (s3 compatible cloud store) in bdv.xml data format
- [misc](https://github.com/mobie-org/mobie/tree/master/data/platynereis/misc) contains miscellaneous data
- [misc/bookmarks](https://github.com/mobie-org/mobie/tree/master/data/platynereis/misc/bookmarks) contains the bookmarks, stored as json files
- [tables](https://github.com/mobie-org/mobie/tree/master/data/platynereis/tables) contains tabular data associated with image segmentation data
- [README.txt](https://github.com/mobie-org/mobie/blob/master/data/platynereis/README.txt) gives a description of this dataset (optional)

#### images

MoBIE uses the [BigDataViewer](https://imagej.net/BigDataViewer) file format to represent image data, either stored locally or on a s3 object store:
- [bdv.n5](https://github.com/bigdataviewer/bigdataviewer-core/blob/master/BDV%20N5%20format.md) for local data
- [bdv.n5.s3](https://github.com/saalfeldlab/n5-aws-s3) for data stored on a s3 compatible object store. Note that this is not part of the official bdv spec yet, [we are working towards merging it](https://github.com/bigdataviewer/bigdataviewer-core/pull/94)

The file `images.json` lists all image data that is available for a dataset.
Currently, we support three different types of image data:
- image: greyscale image data
- segmentation: segmentaiton image data, where each segment is assigned a unique id; supports tables
- mask: binary mask

<!---
TODO explain the image.json format in more detail
-->

#### misc/bookmarks

The bookmark folder must contain a file `default.json` that gives the bookmark for the initial view when opening the dataset.
It may contain additional bookmarks stored as json.

<!---
TODO explain the bookmark format further
-->

#### tables

Tables are stored as `tab separated values` and can be read from the filesystem or git.
For each segmentation with associated tables, the tables are stored in `tables/<IMAGE-NAME>/`.
This folder must contain a table called `default.csv`, it can contain additional tables.
All tables must contain the column `label_id` linking its rows to objects in the segmentation.


## Contributors & Citation

MoBIE is primarily deveopled by Christian Tischer (@tischi), Kimberly Meechan (@K-Meech) and Constantin Pape (@constantinpape).
It has been initially developed for the [PlatyBrowser](https://github.com/mobie-org/platybrowser-datasets) but has since grown in scope. 
In addition, Hernando Martinez Vergara (@HernandoMV), Martin Schorb (@martinschorb) and Valentyna Zinchenko (@vzinche) have contributed to the development.

<!---
TODO additional acknknowledgments:
- Sian for name
- Gemma for logo
- Tobias, Igor, Stephan for java help
- ?
-->

If you use MoBIE for your research, please cite [Whole-body integration of gene expression and single-cell morphology](https://www.biorxiv.org/content/10.1101/2020.02.26.961037v1).
