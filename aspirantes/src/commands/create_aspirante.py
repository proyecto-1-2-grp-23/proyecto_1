from .base_command import BaseCommannd
from ..models.aspirante import AspiranteSchema, Aspirante
from ..session import Session


class CreateAspirante(BaseCommannd):
    def __init__(self, data, userId=None):
        self.data = data

    def execute(self):
        posted_aspirante = AspiranteSchema(
            only=(
                'nombres', 'apellidos', 'correo'
            )
        ).load(self.data)
        aspirante = Aspirante(**posted_aspirante)
        session = Session()

        session.add(aspirante)
        session.commit()

        new_aspirante = AspiranteSchema().dump(aspirante)
        session.close()

        return new_aspirante
