from .base_command import BaseCommannd
from ..models.pruebas import Prueba, PruebaSchema
from ..session import Session


class GetPruebas(BaseCommannd):
    def __init__(self, data, userId=None):
        pass

    def execute(self):
        session = Session()
        prueba = session.query(Prueba).all()
        prueba = PruebaSchema(many=True).dump(prueba)
        session.close()
        return prueba
