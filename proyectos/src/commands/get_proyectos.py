from .base_command import BaseCommannd
from ..models.proyectos import Proyecto, ProyectoSchema
from ..session import Session


class GetProyectos(BaseCommannd):
    def __init__(self, data, userId=None):
        pass

    def execute(self):
        session = Session()
        proyecto = session.query(Proyecto).all()
        proyecto = ProyectoSchema(many=True).dump(proyecto)
        session.close()
        return proyecto
