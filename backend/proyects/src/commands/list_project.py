from ..models.project import Project
from .base_command import BaseCommannd
from ..session import Session
from ..models.project import Project, ProyectoJsonSchema
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify


class ListProject(BaseCommannd):
    def __init__(self, idProyecto):
        self.idProyecto = idProyecto

    def execute(self):
        session = Session()
        proyectos = session.query(Project).filter(Project.id == self.idProyecto).all()

        proyectos_json = ProyectoJsonSchema(many=True).dump(proyectos)

        session.close()
        return proyectos_json
