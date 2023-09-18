from .base_command import BaseCommannd
from ..models.aspirante import Aspirante, AspiranteSchema
from ..session import Session


class GetAspirantes(BaseCommannd):
    def __init__(self, data, userId=None):
        pass

    def execute(self):
        session = Session()
        aspirantes = session.query(Aspirante).all()
        aspirantes = AspiranteSchema(many=True).dump(aspirantes)
        session.close()
        return aspirantes
