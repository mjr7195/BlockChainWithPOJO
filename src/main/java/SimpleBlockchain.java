
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;

// Define a Block class
class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private String data;

    // Constructor
    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.data = data;
        this.hash = calculateHash();
    }

    // Calculate the hash of the block
    public String calculateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = index + timestamp + previousHash + data;
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters
    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public String getData() {
        return data;
    }
}

// Define a Blockchain class
class Blockchain {
    private List<Block> chain;

    // Constructor
    public Blockchain() {
        chain = new ArrayList<Block>();
        // Create the genesis block (the first block in the chain)
        chain.add(new Block(0, "0", "Genesis Block"));
    }

    // Add a new block to the blockchain
    public void addBlock(String data) {
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(previousBlock.getIndex() + 1, previousBlock.getHash(), data);
        chain.add(newBlock);
    }

    public void printBlockchain() {
        for (Block block : chain) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            System.out.println("Data: " + block.getData());
            System.out.println();
        }
    }

}

public class SimpleBlockchain {
    public static void main(String[] args) {

        Gson gson = new Gson();

        // Serialization
        CreditCard creditCard = new CreditCard("John Doe", 1234567865, "2/28", 234);
        String jsonStringCreditCard = gson.toJson(creditCard);

        BankAccount bankAccount = new BankAccount(6783730, 9271453);
        String jsonStringBankAcc = gson.toJson(bankAccount);

        Student student = new Student("Billy Joe", 19, 80);
        String jsonStringStudent = gson.toJson(student);

        // Deserialization
        String inputJsonString = "{\"cardHoldername\":\"Jane Doe\",\"creditCardNum\":1234567865,\"exDate\":\"2/28\",\"secCode\":234}";
        CreditCard deserializedCreditCard = gson.fromJson(inputJsonString, CreditCard.class);

        // Create a new blockchain
        Blockchain blockchain = new Blockchain();

        // Add some blocks to the blockchain
        blockchain.addBlock(jsonStringCreditCard);
        blockchain.addBlock(jsonStringBankAcc);
        blockchain.addBlock(jsonStringStudent);



        // Print the blockchain
        blockchain.printBlockchain();

    }
}
