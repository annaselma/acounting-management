CREATE TABLE WsGeneralLedgerEntity (
                              id UUID PRIMARY KEY,                -- Unique identifier
                              balance DOUBLE NOT NULL,            -- Balance of the transaction
                              category INT NOT NULL,              -- Category of the transaction
                              collectif INT NOT NULL,             -- Collectif field
                              comment VARCHAR(255),               -- Optional comment
                              credit DOUBLE NOT NULL,             -- Credit amount
                              currency VARCHAR(3) NOT NULL,       -- Currency code (e.g., EUR, USD)
                              currency_amount DOUBLE NOT NULL,    -- Amount in the specified currency
                              date DATE NOT NULL,                 -- Transaction date
                              debit DOUBLE NOT NULL,              -- Debit amount
                              description VARCHAR(255) NOT NULL,  -- Description of the transaction
                              header VARCHAR(255) NOT NULL,       -- Header information
                              internal_id VARCHAR(255) NOT NULL,  -- Internal identifier
                              journal_index BIGINT NOT NULL,      -- Journal index
                              last_modify_date DATE NOT NULL,     -- Last modification date
                              name VARCHAR(255) NOT NULL,         -- Name associated with the transaction
                              number VARCHAR(255) NOT NULL,       -- Transaction number
                              payment_method_description VARCHAR(255) NOT NULL, -- Description of payment method
                              period_end VARCHAR(255),            -- End of the transaction period (optional)
                              period_start VARCHAR(255),          -- Start of the transaction period (optional)
                              piece INT NOT NULL,                 -- Piece number
                              qty_unit VARCHAR(255),              -- Quantity unit (optional)
                              quantity INT NOT NULL,              -- Quantity
                              ref VARCHAR(255) NOT NULL,          -- Reference code
                              term VARCHAR(255),                  -- Term information (optional)
                              voucher_id VARCHAR(255),            -- Voucher ID (optional)
                              voucher_ref VARCHAR(255) NOT NULL,  -- Voucher reference
                              account_code VARCHAR(255)           -- Account code (if applicable; optional)
);
