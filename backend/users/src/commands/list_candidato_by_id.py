from .base_command import BaseCommannd
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..session import Session


class ListCandidatoById(BaseCommannd):
    def __init__(self, id):
        self.id = id

    def execute(self):
        session = Session()
        candidato = (
            session.query(Candidato).filter(Candidato.idUsuario == self.id).all()
        )
        datoCandidato = CandidatoJsonSchema(many=True).dump(candidato)
        session.close()
        return datoCandidato
