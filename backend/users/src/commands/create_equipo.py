from .base_command import BaseCommannd
from ..models.user import User, UserSchema, CreatedUserJsonSchema
from ..models.equipo import Equipo, EquipoSchema, EmpresaJsonSchema, CreatedEmpresaJsonSchema
from ..models.empresa import EmpresaSchema, Empresa
from ..session import Session
from flask import Flask, jsonify

class CreateEquipo(BaseCommannd):
    try:
        def __init__(self, data):
            self.data = data
        
        def execute(self):
            empresa_data = {
                "tipoEmpresa": self.data.pop("tipoEmpresa"),
                "razonSocial":self.data.pop("razonSocial"),
                "verticalesNegocio":self.data.pop("verticalesNegocio")

            }
            
            posted_empresa = EmpresaSchema(only=("tipoEmpresa","razonSocial","verticalesNegocio")).load(
                empresa_data
            )
            empresa = Empresa(**posted_empresa)
            posted_equipo = EquipoSchema(only=("nombre", "descripcion","empleados")).load(self.data)
            equipo = Equipo(**posted_equipo, idEmpresa=empresa.id)
            session = Session()

            session.add(empresa)
            session.add(equipo)
            session.commit()
            new_empresa = CreatedEmpresaJsonSchema().dump(empresa)
            session.close()
            return new_empresa
        
        def correo_exist(self, session):
            return len(session.query(User).filter_by().all()) > 0

    except Exception as error:
        # handle the exception
        print("An exception occurred:", error) # An exception occurred: division by zero
        

