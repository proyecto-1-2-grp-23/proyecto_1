from ..models.project import Project
from .base_command import BaseCommannd
from ..session import Session
from ..models.project import Project, ProyectoJsonSchema
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify


class ListProjects(BaseCommannd):
    def execute(self):
        session = Session()
        proyectos = session.query(Project).all()
        proyectos_json = ProyectoJsonSchema(many=True).dump(proyectos)

        session.close()
        return proyectos_json

    # def Listar_id_empresa(self, id_empresa):
    #    session = Session()
    #    equipos = session.query(Equipo).filter_by(id=id_empresa).all()
    #    resultado_equipos = [[equipo.id, equipo.nombre, equipo.descripcion, equipo.empleados] for equipo in equipos]
    #    equipos_dict = [{"id": equipo[0], "nombre": equipo[1], "descripcion": equipo[2], "empleados": equipo[3]} for equipo in resultado_equipos]
    #    return equipos_dict
