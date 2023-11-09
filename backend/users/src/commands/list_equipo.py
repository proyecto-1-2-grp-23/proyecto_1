from .base_command import BaseCommannd
from ..models.user import User, UserSchema, CreatedUserJsonSchema
from ..models.empresa import EmpresaSchema, Empresa, EmpresaJsonSchema
from ..models.equipo import Equipo, EquipoJsonSchema
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify


class ListEquipo(BaseCommannd):
    def execute(self):
        session = Session()
        equipos = session.query(Equipo).all()
        equipos_json = []
        for equipo in equipos:
            equipo_json = EquipoJsonSchema().dump(equipo)
            empresa: Empresa = session.get(Empresa, {"id": empresa.idEmpresa})
            empresa_json: dict = EmpresaJsonSchema().dump(empresa)

            equipo_json = {**equipo_json, "empresa": empresa_json}
            equipos_json.append(equipo_json)
        session.close()
        return equipos_json
