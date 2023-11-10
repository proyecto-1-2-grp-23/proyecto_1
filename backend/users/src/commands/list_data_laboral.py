import json
from .base_command import BaseCommannd
from ..models.data_laboral import DataLaboral
from ..session import Session
from sqlalchemy.orm.attributes import instance_dict


class ListLaboral(BaseCommannd):

    def execute(self):
        session = Session()
        data_laboral = session.query(DataLaboral).all()
        resultado_data_laboral = [json.loads(laboral.habilidades) for laboral in data_laboral]
        resultado_data_laboral = [{"habilidades": laborales} for laborales in resultado_data_laboral]        
        return resultado_data_laboral