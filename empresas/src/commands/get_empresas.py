from .base_command import BaseCommannd
from ..models.empresas import Empresa, EmpresaSchema
from ..session import Session


class GetEmpresas(BaseCommannd):
    def __init__(self, data, userId=None):
        pass

    def execute(self):
        session = Session()
        empresa = session.query(Empresa).all()
        empresa = EmpresaSchema(many=True).dump(empresa)
        session.close()
        return empresa
