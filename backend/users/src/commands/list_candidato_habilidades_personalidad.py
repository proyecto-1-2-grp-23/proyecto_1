from .base_command import BaseCommannd
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..session import Session


class ListCandidatoHabPer(BaseCommannd):
    def __init__(self, personalidad):
        self.personalidad = personalidad

    def execute(self):
        session = Session()
        personalidad = (
            session.query(Candidato)
            .filter(Candidato.rasgosPersonalidad.like("%" + self.personalidad + "%"))
            .all()
        )
        candidatos = CandidatoJsonSchema(many=True).dump(personalidad)
        session.close()
        return candidatos
