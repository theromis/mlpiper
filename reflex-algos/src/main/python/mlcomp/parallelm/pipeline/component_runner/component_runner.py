import abc
from parallelm.common.base import Base


class ComponentRunner(Base):
    def __init__(self, ml_engine, dag_node):
        __metaclass__ = abc.ABCMeta  # Indicates an abstract class
        super(ComponentRunner, self).__init__(ml_engine.get_engine_logger(self.logger_name()))
        self._logger.debug("Creating pipeline component: " + self.name())
        self._ml_engine = ml_engine
        self._dag_node = dag_node
        self._params = None

    def configure(self, params):
        self._logger.info("Configure component with input params, name: {}, params: {}"
                          .format(self.name(), params))
        self._params = params

    @abc.abstractmethod
    def run(self, parent_data_objs):
        pass

