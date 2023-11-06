from .base_command import BaseCommannd
from ..models.empresa import Empresa, EmpresaJsonSchema
from ..session import Session


class ListEmpresa(BaseCommannd):

    def execute(self):
        session = Session()
        admin_empresas = session.query(Empresa).all()
        empresas = EmpresaJsonSchema(many=True).dump(admin_empresas)
        session.close()
        return empresas