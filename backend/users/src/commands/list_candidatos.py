from .base_command import BaseCommannd
from ..models.candidato import Candidato,  ListarCandidatoJsonSchema
from ..session import Session


class ListCandidato(BaseCommannd):

    def execute(self):
        session = Session()
        admin_candidatos = session.query(Candidato).all()
        candidatos = ListarCandidatoJsonSchema(many=True).dump(admin_candidatos)
        session.close()
        return candidatos