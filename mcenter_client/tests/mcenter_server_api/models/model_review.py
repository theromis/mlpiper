# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from mcenter_server_api.models.base_model_ import Model
from mcenter_server_api import util


class ModelReview(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, action=None, description=None, ml_app_instance_id=None):  # noqa: E501
        """ModelReview - a model defined in OpenAPI

        :param action: The action of this ModelReview.  # noqa: E501
        :type action: str
        :param description: The description of this ModelReview.  # noqa: E501
        :type description: str
        :param ml_app_instance_id: The ml_app_instance_id of this ModelReview.  # noqa: E501
        :type ml_app_instance_id: str
        """
        self.openapi_types = {
            'action': 'str',
            'description': 'str',
            'ml_app_instance_id': 'str'
        }

        self.attribute_map = {
            'action': 'action',
            'description': 'description',
            'ml_app_instance_id': 'mlAppInstanceId'
        }

        self._action = action
        self._description = description
        self._ml_app_instance_id = ml_app_instance_id

    @classmethod
    def from_dict(cls, dikt) -> 'ModelReview':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ModelReview of this ModelReview.  # noqa: E501
        :rtype: ModelReview
        """
        return util.deserialize_model(dikt, cls)

    @property
    def action(self):
        """Gets the action of this ModelReview.


        :return: The action of this ModelReview.
        :rtype: str
        """
        return self._action

    @action.setter
    def action(self, action):
        """Sets the action of this ModelReview.


        :param action: The action of this ModelReview.
        :type action: str
        """

        self._action = action

    @property
    def description(self):
        """Gets the description of this ModelReview.


        :return: The description of this ModelReview.
        :rtype: str
        """
        return self._description

    @description.setter
    def description(self, description):
        """Sets the description of this ModelReview.


        :param description: The description of this ModelReview.
        :type description: str
        """

        self._description = description

    @property
    def ml_app_instance_id(self):
        """Gets the ml_app_instance_id of this ModelReview.


        :return: The ml_app_instance_id of this ModelReview.
        :rtype: str
        """
        return self._ml_app_instance_id

    @ml_app_instance_id.setter
    def ml_app_instance_id(self, ml_app_instance_id):
        """Sets the ml_app_instance_id of this ModelReview.


        :param ml_app_instance_id: The ml_app_instance_id of this ModelReview.
        :type ml_app_instance_id: str
        """

        self._ml_app_instance_id = ml_app_instance_id
