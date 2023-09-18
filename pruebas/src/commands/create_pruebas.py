from .base_command import BaseCommannd
from ..models.pruebas import PruebaSchema, Prueba
from ..session import Session


class CreatePrueba(BaseCommannd):
    def __init__(self, data, userId=None):
        self.data = data

    def execute(self):
        posted_prueba = PruebaSchema(
            only=(
                'titulo', 'description', 'supervisor'
            )
        ).load(self.data)
        prueba = Prueba(**posted_prueba)
        session = Session()

        session.add(prueba)
        session.commit()

        new_prueba = PruebaSchema().dump(prueba)
        session.close()

        return new_prueba
