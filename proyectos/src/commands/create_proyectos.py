from .base_command import BaseCommannd
from ..models.proyectos import ProyectoSchema, Proyecto
from ..session import Session


class CreateProyecto(BaseCommannd):
    def __init__(self, data, userId=None):
        self.data = data

    def execute(self):
        posted_proyecto = ProyectoSchema(
            only=(
                'nombre', 'description', 'objetivo'
            )
        ).load(self.data)
        proyecto = Proyecto(**posted_proyecto)
        session = Session()

        session.add(proyecto)
        session.commit()

        new_proyecto = ProyectoSchema().dump(proyecto)
        session.close()

        return new_proyecto
