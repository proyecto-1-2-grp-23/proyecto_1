from .base_command import BaseCommannd

from ..models.desempeño import Desempeño, DesempeñoSchema, CreatedDesempeñoJsonSchema
from ..models.habilidad import Habilidad, HabilidadSchema, CreatedHabilidadJsonSchema
from ..session import Session


class CreateDesempeño(BaseCommannd):
    def __init__(self, data):
        self.data = data

    def execute(self):
        desempeño_data = {
            "idPrueba": self.data.pop("idPrueba"),
            "idCandidato": self.data.pop("idCandidato"),
            "idEmpresa": self.data.pop("idEmpresa")
        }
        posted_desempeño = DesempeñoSchema(
            only=(
                "idPrueba",
                "idCandidato",
                "idEmpresa",
            )
        ).load(desempeño_data)

        desempeño = Desempeño(**posted_desempeño)
        session = Session()
        
        session.add(desempeño)
        session.commit()
        new_desempeño = CreatedDesempeñoJsonSchema().dump(desempeño)
        session.close()
        
        
        habilidad_data = {
            "idPrueba": new_desempeño['id'],
            "habilidad":self.data.pop("habilidad"),
            "puntaje": self.data.pop("puntaje")
        }
        posted_habilidad = HabilidadSchema(
            only=(
                "idPrueba",
                "habilidad",
                "puntaje"
            )
        ).load(habilidad_data)
        habilidad = Habilidad(**posted_habilidad)
        session.add(habilidad)
        session.commit()
        new_habilidad = CreatedHabilidadJsonSchema().dump(habilidad)
        return new_habilidad