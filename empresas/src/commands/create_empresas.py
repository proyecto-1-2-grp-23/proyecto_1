from .base_command import BaseCommannd
from ..models.empresas import EmpresaSchema, Empresa
from ..session import Session


class CreateEmpresa(BaseCommannd):
    def __init__(self, data, userId=None):
        self.data = data

    def execute(self):
        posted_empresa = EmpresaSchema(
            only=(
                'nombre', 'nit', 'correo'
            )
        ).load(self.data)
        empresa = Empresa(**posted_empresa)
        session = Session()

        session.add(empresa)
        session.commit()

        new_empresa = EmpresaSchema().dump(empresa)
        session.close()

        return new_empresa
