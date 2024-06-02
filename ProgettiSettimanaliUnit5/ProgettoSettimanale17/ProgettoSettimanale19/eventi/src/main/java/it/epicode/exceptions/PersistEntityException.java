package it.epicode.exceptions;

import it.epicode.dto.DtoBase;

public class PersistEntityException extends ServiceException {
    private static final long serialVersionUID = 1L;

    /**
     * Dato per cui non è stato possibile il salvataggio.
     */
    public final DtoBase invalidDto;

    public PersistEntityException(DtoBase invalidDto) {
        this.invalidDto = invalidDto;
    }
}
