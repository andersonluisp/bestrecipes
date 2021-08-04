package com.andersonpimentel.bestrecipes.app

import com.andersonpimentel.bestrecipes.R

class ImportDataException : Exception() {
    override val message: String = R.string.import_data_error_message.print()
}
