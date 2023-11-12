from .base_command import BaseCommannd
from ..models.data_laboral import DataLaboral, DataLaboralJsonSchema
from ..session import Session


class ListCandidatoHabTec(BaseCommannd):
    def __init__(self, tecnica):
        self.tecnica = tecnica

    def execute(self):
        session = Session()
        tecnica = (
            session.query(DataLaboral)
            .filter(DataLaboral.habilidades.like("%" + self.tecnica + "%"))
            .all()
        )
        candidatos = DataLaboralJsonSchema(many=True).dump(tecnica)
        session.close()
        return candidatos
