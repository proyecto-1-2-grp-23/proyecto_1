import json
from ..models.project import Project
from .base_command import BaseCommannd
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify


class ListProjectsById(BaseCommannd):
    def __init__(self, id_proyecto):
        self.id_proyecto = id_proyecto


    def execute(self):
        id_proyecto = self.id_proyecto
        session = Session()
        proyectos = session.query(Project).filter_by(id=id_proyecto).all()
        resultado_proyectos= [[proyecto.id, proyecto.conocimientos_tecnicos, proyecto.habilidades_blandas] for proyecto in proyectos]
        proyectos_dict = [{"id": proyecto[0], "conocimientos_tecnicos": proyecto[1], "habilidades_blandas": proyecto[2]} for proyecto in resultado_proyectos]
        conocimientos_tecnicos = proyectos_dict[0]['conocimientos_tecnicos']
        habilidades_blandas = proyectos_dict[0]['habilidades_blandas']

        
        conocimientos_tecnicos = json.loads(conocimientos_tecnicos)
        habilidades_blandas = json.loads(habilidades_blandas)

        return conocimientos_tecnicos, habilidades_blandas