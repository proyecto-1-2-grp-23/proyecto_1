from ...src.session import Session
from ...src.models.proyect_candidato import ProyectCandidato, ProyectCandidatoJsonSchema
from ..commands.base_command import BaseCommannd


class ListProjectsCandidato(BaseCommannd):
    def __init__(self, idCandidato):
        self.idCandidato = idCandidato

    def execute(self):
        session = Session()
        proyectos = (
            session.query(ProyectCandidato).filter(ProyectCandidato.idCandidato == self.idCandidato).all()
        )

        proyectos_json = ProyectCandidatoJsonSchema(many=True).dump(proyectos)

        session.close()
        return proyectos_json