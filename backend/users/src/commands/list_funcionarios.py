from .base_command import BaseCommannd
from ..models.funcionario import DataFuncionario, DataFuncionarioJsonSchema
from ..session import Session


class ListFuncionarios(BaseCommannd):
    def __init__(self, idEmpresa):
        self.idEmpresa = idEmpresa

    def execute(self):
        session = Session()
        funcionario = (
            session.query(DataFuncionario)
            .filter(DataFuncionario.idEmpresa == self.idEmpresa)
            .all()
        )
        empresas = DataFuncionarioJsonSchema(many=True).dump(funcionario)
        session.close()
        return empresas
