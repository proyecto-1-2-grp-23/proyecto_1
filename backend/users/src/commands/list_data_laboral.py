from .base_command import BaseCommannd
from ..models.data_laboral import DataLaboral, DataLaboralSchema
from ..session import Session


class ListDataLaboral(BaseCommannd):
    def execute(self):
        session = Session()
        data = session.query(DataLaboral).all()
        data_laboral = DataLaboralSchema(many=True).dump(data)
        session.close()
        return data_laboral
