# Minimal makefile for Sphinx documentation
#

# You can set these variables from the command line.
SPHINXOPTS    =
SPHINXBUILD   = python3 -msphinx
SPHINXPROJ    = ParallelM
SOURCEDIR     = doc-src
BUILDDIR      = build

DISTDIR = ""

MLOPS_EGG_PY2_SYMLINK = "mlops-py2.egg"
MLOPS_EGG_PY3_SYMLINK = "mlops-py3.egg"

# Put it first so that "make" without argument is like "make help".
help:
	@$(SPHINXBUILD) -M help "$(SOURCEDIR)" "$(BUILDDIR)" $(SPHINXOPTS) $(O)

.PHONY: help Makefile bdist

# Catch-all target: route all unknown targets to Sphinx using the new
# "make mode" option.  $(O) is meant as a shortcut for $(SPHINXOPTS).
%: Makefile
	@$(SPHINXBUILD) -M $@ "$(SOURCEDIR)" "$(BUILDDIR)" $(SPHINXOPTS) $(O)


bdist:
	python setup.py bdist

egg:
	rm -rf ./dist
	python2 setup.py -q bdist_egg
	python3 setup.py -q bdist_egg

MLOPS_EGG_PY2 = $(shell realpath $(shell ls ./dist/*-py2.*.egg))
MLOPS_EGG_PY3 = $(shell realpath $(shell ls ./dist/*-py3.*.egg))
MLOPS_EGG_PY2_BASENAME = $(shell basename $(MLOPS_EGG_PY2))
MLOPS_EGG_PY3_BASENAME = $(shell basename $(MLOPS_EGG_PY3))
SPARK_JARS = "../reflex-algos/target/ReflexAlgos-jar-with-dependencies.jar"

install_to: egg
ifneq ($(DISTDIR),"")
	mkdir -p $(DISTDIR)
	cp -f $(MLOPS_EGG_PY2) $(MLOPS_EGG_PY3) $(DISTDIR)
	cd $(DISTDIR); ln -sf $(MLOPS_EGG_PY2_BASENAME) $(MLOPS_EGG_PY2_SYMLINK); cd -
	cd $(DISTDIR); ln -sf $(MLOPS_EGG_PY3_BASENAME) $(MLOPS_EGG_PY3_SYMLINK); cd -
endif

local: egg
	cd dist; ln -sf $(MLOPS_EGG_PY2_BASENAME) $(MLOPS_EGG_PY2_SYMLINK); cd -
	cd dist; ln -sf $(MLOPS_EGG_PY3_BASENAME) $(MLOPS_EGG_PY3_SYMLINK); cd -


wheel:
	python2 setup.py sdist bdist_wheel
	python3 setup.py sdist bdist_wheel

test: egg
	env PYTHONPATH=./:tests/:${SPARK_HOME}/python python2 -m py.test tests/
	env PYTHONPATH=./:tests/:${SPARK_HOME}/python python3 -m pytest tests/

cover: egg
	env PYTHONPATH=./ coverage2 run -m py.test tests/
	coverage2 report --include "parallelm/mlops/*"

# The target test can be used to run a simple example directly.
# Please define the environment variable SPARK_SUBMIT to point to a spark-submit binary
test_pyspark: egg
	env PM_MLOPS_MODE=pyspark \
		PM_REST_SERVER_WAIT_FOR_EXIT=0 \
		${SPARK_SUBMIT} \
				  --master spark://localhost:7077 \
				  --py-files $(MLOPS_EGG_PY2) \
				  --jars $(SPARK_JARS) \
				  examples/pi_calc.py

test_tf: egg
	env PM_MLOPS_MODE=tensorflow \
		PM_REST_SERVER_WAIT_FOR_EXIT=0 \
		PM_MLOPS_FILE="/tmp/tf_mlops.channel" \
		${SPARK_SUBMIT} \
				  --master spark://localhost:7077 \
				  --py-files $(MLOPS_EGG_PY2) \
				  --jars $(SPARK_JARS)) \
				  examples/pi_calc.py

test_python: egg
	env PM_MLOPS_MODE=python \
		PM_MLOPS_FILE="/tmp/mlops.channel" \
		PYTHONPATH=$(MLOPS_EGG_PY2) \
		python2 examples/mlops_example_python.py
	@echo "========"
	cat /tmp/mlops.channel

test_non_pm: egg
	env PM_MLOPS_FILE="/tmp/mlops.csv"\
	${SPARK_SUBMIT} \
				  --master spark://localhost:7077 \
				  --py-files $(MLOPS_EGG_PY2) \
				  --jars $(SPARK_JARS) \
				  examples/pi_calc.py

install: wheel
	env PYTHONPATH=./ bin/install_locally.py

clean:
	\rm -rf dist
	\rm -rf build
