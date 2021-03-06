:orphan:

.. _mlops_env:

#################
MLOps Environment
#################

Overview
--------

MLOps has a hierarchical execution structure.
At the top level of the hierarchy, we have the MLApp (application) also referred to as an ION (Intelligent
Overlay Network) which represents an application. Each MLApp may contain multiple pipelines, where each
pipeline represents a single flow of ML computation on a given framework (e.g., Spark, python, etc.).
In turn, each pipeline may consist of one or more components, where each of these components is a program.
Any or all of the components may use the mlops library to communicate with MCenter, and via MLOps, components
may communicate statistics, events, models, etc. to each other.

Concepts
--------

ParallelM environments define the following concepts:

* Model: a machine learning or deep learning model. This could be in various formats, for example, PMML or SavedModel.
* Component: a program or computational unit. A component may generate a model if it is part of a training
  pipeline or it may use a model if it is part of an inference pipeline.
* Pipeline: a set of one or more components chained together.
  For example: a pipeline may consist of an input processing component, whose output is processed by a feature
  engineering component, whose output is passed to a component that performs ML training.
* MLAppNode: An MLAppNode contains the template for a pipeline as well as scheduling and other meta-information about the
  pipeline.
* MLApp: an application consisting of one or more coordinated instantiations of MLAppNodes.
  For example, an application may have one pipeline for training models (a model producer), another pipeline that
  accepts the trained models and performs inferences with them (a model consumer), and yet another pipeline that
  compares the the output of the inference pipeline against a known standard (e.g., a canary pipeline).
* Agent: ParallelM deploys an agent to each host that runs MLOps jobs. The agents are used to manage pipelines
  running on that host.
* MLApp Policy: policy information about the MLApp. Policy nodes capture policy configuration information about the MLApp.
  For example: one policy is whether newly trained models from one pipeline should be automatically deployed to another
  pipeline for use in inferences or whether deployment of new models should be manually triggered.
* Event: a notification sent from a processing node to the MLOps infrastructure.
* Alert: a type of event with elevated visibility and priority.
* Statistics: measurements in the form of scalars, time series, tables, histograms, etc.
* KPI: business metrics associated with the MLApp. KPI may include arbitrary scalar values associated with the business.
  Unlike statistics, which are generated and reported by MLAppNodes as they run, KPI can be uploaded with timestamps that
  have passed. This allows metrics not generated by the MLApp itself to be correlated with the MLApp's statistics by time.

MLOps Interface to its Environment
----------------------------------

When running in ParallelM's environment, the mlops module can access information about which MLApp, Pipeline, and MLAppNode
it is running as part of. It can query which models have been produced by the pipeline and which agents are running as
part of the MLApp. This data is stored in the database and can be retrieved directly from the database or by
downloading a Timeline Capture.

This interface is still under development and will be fleshed out in future releases.

Predefined Statistics
---------------------

Some statistics have special meaning in the MCenter UI. In the current release, the count of total predictions is shown
in the Health View. To report this statistic via the MLOps API, use the predefined name when updating its value:

    >>> from parallelm.mlops import mlops
    >>> from parallelm.mlops.predefined_stats import PredefinedStats
    >>> mlops.init()
    >>> mlops.set_stat(PredefinedStats.PREDICTIONS_COUNT, number_of_predictions)
    >>> mlops.done()