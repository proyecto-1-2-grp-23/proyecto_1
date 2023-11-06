from .base_command import BaseCommannd
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..session import Session


class ListCandidato(BaseCommannd):

    def execute(self):
        session = Session()
        admin_candidatos = session.query(Candidato).all()
        candidatos = CandidatoJsonSchema(many=True).dump(admin_candidatos)
        session.close()
        return candidatos