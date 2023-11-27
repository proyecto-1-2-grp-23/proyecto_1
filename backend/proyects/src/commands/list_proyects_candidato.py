from ..models.project import Project
from .base_command import BaseCommannd
from ..session import Session
from ..models.proyect_candidato import ProyectCandidato, ProyectCandidatoJsonSchema
from ..models.project import Project, ProyectoJsonSchema
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify


class ListProjectsCandidato(BaseCommannd):
    def __init__(self, idCandidato):
        self.idCandidato = idCandidato

    def execute(self):
        session = Session()
        proyectos = (
            session.query(ProyectCandidato)
            .filter(ProyectCandidato.idCandidato == self.idCandidato)
            .all()
        )

        proyectos_json = []
        for proyctos in proyectos:
            proyectoCand_json = ProyectCandidatoJsonSchema().dump(proyctos)
            proyecto: Project = session.get(Project, {"id": proyctos.idProyecto})
            proyecto_json: dict = ProyectoJsonSchema().dump(proyecto)

            proyectoCand_json = {**proyectoCand_json, "proyecto": proyecto_json}
            proyectos_json.append(proyectoCand_json)
        session.close()

        if not proyectos_json:
            return {"resultado": 0}
        else:
            return {"resultado": proyectos_json}
