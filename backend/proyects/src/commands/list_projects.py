from ..models.project import Project
from .base_command import BaseCommannd
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify


class ListProjects(BaseCommannd):

    def execute(self):
        session = Session()
        proyectos = session.query(Project).all()
        resultado_proyectos= [[proyecto.id, proyecto.nombre, proyecto.descripcion] for proyecto in proyectos]
        proyectos_dict = [{"id": proyecto[0], "nombre": proyecto[1], "descripcion": proyecto[2]} for proyecto in resultado_proyectos]
        return proyectos_dict


    #def Listar_id_empresa(self, id_empresa):
    #    session = Session()
    #    equipos = session.query(Equipo).filter_by(id=id_empresa).all()
    #    resultado_equipos = [[equipo.id, equipo.nombre, equipo.descripcion, equipo.empleados] for equipo in equipos]
    #    equipos_dict = [{"id": equipo[0], "nombre": equipo[1], "descripcion": equipo[2], "empleados": equipo[3]} for equipo in resultado_equipos]
    #    return equipos_dict

    
        