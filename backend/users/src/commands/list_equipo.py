from .base_command import BaseCommannd
from ..models.user import User, UserSchema, CreatedUserJsonSchema
from ..models.empresa import EmpresaSchema, Empresa
from ..models.equipo import Equipo
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify


class ListEquipo(BaseCommannd):

    def execute(self):
        session = Session()
        equipos = session.query(Equipo).all()
        resultado_equipos = [[equipo.id, equipo.nombre, equipo.descripcion] for equipo in equipos]
        equipos_dict = [{"id": equipo[0], "nombre": equipo[1], "descripcion": equipo[2]} for equipo in resultado_equipos]
        return equipos_dict


    #def Listar_id_empresa(self, id_empresa):
    #    session = Session()
    #    equipos = session.query(Equipo).filter_by(id=id_empresa).all()
    #    resultado_equipos = [[equipo.id, equipo.nombre, equipo.descripcion, equipo.empleados] for equipo in equipos]
    #    equipos_dict = [{"id": equipo[0], "nombre": equipo[1], "descripcion": equipo[2], "empleados": equipo[3]} for equipo in resultado_equipos]
    #    return equipos_dict