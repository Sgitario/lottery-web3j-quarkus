pragma solidity >=0.5.0 <0.6.0;

contract Lottery {
    address public manager;
    address payable[] private players;
    address payable winner;

    constructor() public {
        manager = msg.sender;
    }
    
    function enter() public payable {
        require(msg.value > .01 ether, "Must have at least 0.01 ether");
        
        players.push(msg.sender);
    }
    
    function pickWinner() public restricted {
        uint index = random() % players.length;
        winner = address(players[index]);
        address contest = address(this);
        winner.transfer(contest.balance);
        players = new address payable[](0);
    }
    
    function getWinner() public restricted view returns (address) {
        return winner;
    }
    
    function getPlayers() public restricted view returns (address payable[] memory) {
        return players;
    }
    
    function random() private view returns (uint) {
        return uint(keccak256(abi.encodePacked(block.difficulty, now, players)));
    }
    
    modifier restricted() {
        require(manager == msg.sender, "Only Manager can pick the winner");
        _;
    }
}