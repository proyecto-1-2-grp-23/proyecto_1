from .base_command import BaseCommannd
from ..models.funcionario import DataFuncionario, DataFuncionarioJsonSchema
from ..session import Session


class ListFuncionarios(BaseCommannd):
    def execute(self):
        session = Session()
        funcionario = session.query(DataFuncionario).all()
        empresas = DataFuncionarioJsonSchema(many=True).dump(funcionario)
        session.close()
        return empresas
