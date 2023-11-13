from ..models.project import Project
from .base_command import BaseCommannd
from ..session import Session
from ..models.project import Project, ProyectoJsonSchema
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify


class ListProjectsEmpresa(BaseCommannd):
    def __init__(self, idEmpresa):
        self.idEmpresa = idEmpresa

    def execute(self):
        session = Session()
        proyectos = (
            session.query(Project).filter(Project.idEmpresa == self.idEmpresa).all()
        )

        proyectos_json = ProyectoJsonSchema(many=True).dump(proyectos)

        session.close()
        return proyectos_json
